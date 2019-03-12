package com.example.homework3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;


import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    SeekBar counterSeekbar;
    SeekBar.OnSeekBarChangeListener seekListener;
    CharSequence builder = null;
    String input = null;
    SearchView searchView;
    ArrayList<MusicList> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterSeekbar = findViewById(R.id.seekbar);
        searchView = (SearchView) findViewById(R.id.searchBox);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            counterSeekbar.setMin(10);
        }
        counterSeekbar.setMax(30);
        seekListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(MainActivity.this,
                        "Seekbar vale " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        findViewById(R.id.searchbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    builder = searchView.getQuery();
                    if (builder.toString().contains(" ")) {
                        input = builder.toString().replaceAll(" ", "+");
                    }
                    else{
                        input = builder.toString();
                    }
                    ProgressDialog progress = new ProgressDialog(MainActivity.this);
                    progress.setTitle("Loading");
                    progress.setMessage("Wait while loading...");
                    progress.setCancelable(false);
                    progress.show();
                    new GetDataAsync().execute("https://itunes.apple.com/search?term=" + input + "&limit=" + counterSeekbar.getProgress());
                    progress.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Connect to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.resetbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                counterSeekbar.resetPivot();
                searchView.setQuery("", false);
                searchView.clearFocus();
                counterSeekbar.setProgress(10);

            }

        });
    }


    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<MusicList>> {
        MusicList musicList;
        @Override
        protected ArrayList<MusicList> doInBackground(String... params) {

            HttpURLConnection connection = null;
            final ArrayList<MusicList> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int test = connection.getResponseCode();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                    JSONObject root = new JSONObject(json);
                    JSONArray articles = root.getJSONArray("results");
                    Log.d("articles",articles.getString(0));
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject newJson = articles.getJSONObject(i);
                        musicList = new MusicList();
                        musicList.trackName = newJson.getString("trackName");
                        musicList.primaryGenreName = newJson.getString("primaryGenreName");
                        musicList.artistName = newJson.getString("artistName");
                        musicList.trackName = newJson.getString("trackName");
                        musicList.collectionName = newJson.getString("collectionName");
                        musicList.trackPrice = newJson.getInt("trackPrice");
                        musicList.collectionPrice = newJson.getInt("collectionPrice");
                        musicList.releaseDate = newJson.getString("releaseDate");
                        musicList.releaseDate=musicList.releaseDate.substring(0, 10);
                        musicList.artworkUrl100 = newJson.getString("artworkUrl30");
                        result.add(musicList);
                    }
                    Switch sw = (Switch) findViewById(R.id.switch1);
                    sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                Collections.sort(result, new Comparator<MusicList>() {
                                    @Override
                                    public int compare(MusicList t1, MusicList t2) {
                                        return Integer.valueOf(t1.getTrackPrice()).compareTo(t2.getTrackPrice());
                                    }
                                });

                            } else {
                            }
                        }
                    });
                ArrayList<MusicList> data = new ArrayList<>();
                    Log.d("array",result.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }
        @Override
        protected void onPostExecute(ArrayList<MusicList> result) {
            items.clear();
            if (result.size() > 0) {
                items.addAll(result);
                displayMusicList(items);
            } else {
                Log.d("Empty List", "empty result");
            }
        }


        private void displayMusicList(final ArrayList<MusicList> result) {
            ListView listView = (ListView)findViewById(R.id.listView);
            MusicAdapter adapter = new MusicAdapter(MainActivity.this, R.layout.music_list, result);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MainActivity.this, DisplayDetail.class);
                    MusicList music = result.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("music", music);
                    i.putExtra( "bundle" , bundle);
                    startActivity(i);
                }
            });

        }
    }
}


