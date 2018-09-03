package com.word.radio;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button speech;
    private MediaPlayer mediaPlayer;
    private TextToSpeech textToSpeech;
    private String words, chinese, english;
    private boolean isPlaying;
    private Pattern p = Pattern.compile("\\d.*?\t(\\w+?)：(.*)");
    private Matcher m;
    private boolean isExit;
    private boolean isReadChinese;
    private MenuItem menuItem;
    private MenuItem menuItem1;
    private ProgressBar mProgressBarHorizontal;
    private SharedPreferences sharedPreferences = null;
    private boolean flag;
    private SharedPreferences.Editor editor = null;

    private int times = 2;
    // Spinner对象
    private Spinner spinner;
    final HashMap ttsOptions = new HashMap<>();
    TextView textView, textView1, allWordTextView;
    // 数据源
    private String[] array = {"part 1", "part 2", "part 3", "part 4", "part 5", "part 6", "part 7", "part 8", "part 9", "part 10", "part 11",
            "part 12", "part 13", "part 14", "part 15", "part 16", "part 17", "part 18", "part 19", "part 20", "part 21", "part 22", "part 23",
            "part 24", "part 25", "part 26", "part 27", "part 28", "part 29", "part 30", "part 31", "part 32", "part 33", "part 34",};

    private AudioManager mAudioManager;
    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttsOptions.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "utterance");
        textView = (TextView) findViewById(R.id.look);
        textView1 = (TextView) findViewById(R.id.chinese);
        allWordTextView = (TextView) findViewById(R.id.allWords);
        mProgressBarHorizontal = (ProgressBar) findViewById(R.id.progressBarHorizontal);
        //线控广播接收器
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // AudioManager注册一个MediaButton对象
        mComponentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());

        //程序内部广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ok");
        intentFilter.addAction("restart");
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter);
        /*
        MyRestartBroadcastReceiver myRestartBroadcastReceiver = new MyRestartBroadcastReceiver();
        registerReceiver(myRestartBroadcastReceiver, )*/

        mediaPlayer = new MediaPlayer();
        // 实例化对象
        spinner = (Spinner) findViewById(R.id.spinner);
        // 添加选中条目的点击事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 选中任意的条目后会触发
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_1");
                        m = p.matcher(words);
                        break;
                    case 1:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_2");
                        m = p.matcher(words);
                        break;
                    case 2:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_3");
                        m = p.matcher(words);
                        break;
                    case 3:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_4");
                        m = p.matcher(words);
                        break;
                    case 4:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_5");
                        m = p.matcher(words);
                        break;
                    case 5:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_6");
                        m = p.matcher(words);
                        break;
                    case 6:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_7");
                        m = p.matcher(words);
                        break;
                    case 7:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_8");
                        m = p.matcher(words);
                        break;
                    case 8:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_9");
                        m = p.matcher(words);
                        break;
                    case 9:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_10");
                        m = p.matcher(words);
                        break;
                    case 10:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_11");
                        m = p.matcher(words);
                        break;
                    case 11:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_12");
                        m = p.matcher(words);
                        break;
                    case 12:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_13");
                        m = p.matcher(words);
                        break;
                    case 13:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_14");
                        m = p.matcher(words);
                        break;
                    case 14:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_15");
                        m = p.matcher(words);
                        break;
                    case 15:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_16");
                        m = p.matcher(words);
                        break;
                    case 16:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_17");
                        m = p.matcher(words);
                        break;
                    case 17:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_18");
                        m = p.matcher(words);
                        break;
                    case 18:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_19");
                        m = p.matcher(words);
                        break;
                    case 19:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_20");
                        m = p.matcher(words);
                        break;
                    case 20:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_21");
                        m = p.matcher(words);
                        break;
                    case 21:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_22");
                        m = p.matcher(words);
                        break;
                    case 22:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_23");
                        m = p.matcher(words);
                        break;
                    case 23:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_24");
                        m = p.matcher(words);
                        break;
                    case 24:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_25");
                        m = p.matcher(words);
                        break;
                    case 25:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_26");
                        m = p.matcher(words);
                        break;
                    case 26:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_27");
                        m = p.matcher(words);
                        break;
                    case 27:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_28");
                        m = p.matcher(words);
                        break;
                    case 28:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_29");
                        m = p.matcher(words);
                        break;
                    case 29:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_30");
                        m = p.matcher(words);
                        break;
                    case 30:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_31");
                        m = p.matcher(words);
                        break;
                    case 31:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_32");
                        m = p.matcher(words);
                        break;
                    case 32:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_33");
                        m = p.matcher(words);
                        break;
                    case 33:
                        words = ReadFile.readAssetsFile(getApplicationContext(), "part_34");
                        m = p.matcher(words);
                        break;
                }
                mProgressBarHorizontal.setProgress(0);
                allWordTextView.setText(words.replaceAll("\\t", ".   "));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                words = ReadFile.readAssetsFile(getApplicationContext(), "part_1");
                m = p.matcher(words);
                mProgressBarHorizontal.setProgress(0);
                allWordTextView.setText(words.replaceAll("\\t", ".   "));
            }
        });
        //创建适配器对象
        SpinnerAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, array);
        //给Spinner设置适配器
        spinner.setAdapter(adapter);

        speech = (Button) findViewById(R.id.speech);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseOrPlay();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.readChinese);
        menuItem1 = menu.findItem(R.id.readTimes);
        sharedPreferences = getSharedPreferences("read", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();//获取编辑器
        flag = sharedPreferences.getBoolean("read", false);
        if (flag == true) {
            menuItem.setIcon(R.mipmap.not_chinese);
            menuItem.setTitle(R.string.not_read_chinese);
        } else {
            menuItem.setIcon(R.mipmap.chinese);
            menuItem.setTitle(R.string.read_chinese);
        }
        isReadChinese = !flag;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.readChinese:
                flag = sharedPreferences.getBoolean("read", false);
                if (flag == true) {
                    editor.putBoolean("read", false);
                    isReadChinese = true;
                    menuItem.setIcon(R.mipmap.chinese);
                    menuItem.setTitle(R.string.read_chinese);
                    Toast.makeText(getApplicationContext(), R.string.read_chinese, Toast.LENGTH_SHORT).show();
                } else {
                    editor.putBoolean("read", true);
                    isReadChinese = false;
                    menuItem.setIcon(R.mipmap.not_chinese);
                    menuItem.setTitle(R.string.not_read_chinese);
                    Toast.makeText(getApplicationContext(), R.string.not_read_chinese, Toast.LENGTH_SHORT).show();
                }
                editor.commit();//提交修改
                break;
            case R.id.readTimes:
                if (times == 2) {
                    menuItem1.setTitle(R.string.read_times_two);
                    times = 1;
                } else {
                    menuItem1.setTitle(R.string.read_times);
                    times = 2;
                }
            default:
                break;
        }
        return true;
    }

    public void pauseOrPlay() {
        if (!isPlaying) initTts();
        if (!isPlaying && mediaPlayer != null && textToSpeech != null) {
            beginSpeech();
            count = 0;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    speech.setText(R.string.pause);
                }
            });
            isPlaying = !isPlaying;
        } else if (isPlaying && mediaPlayer != null && textToSpeech != null) {
            textToSpeech.shutdown();
            mediaPlayer.stop();
            mediaPlayer.reset();
            count = 0;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    speech.setText(R.string.begin);
                }
            });
            isPlaying = !isPlaying;
        }
    }

    private void initTts() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == textToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                            && result != TextToSpeech.LANG_AVAILABLE) {
                        Toast.makeText(MainActivity.this, "TTS暂时不支持这种语音的朗读！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textToSpeech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
            // 对发音结束的监听
            @Override
            public void onUtteranceCompleted(String utteranceId) {
                //Log.e("ttslog", "发音完成");
                beginSpeech();
            }
        });
    }

    //播放单词
    private void beginSpeech() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (m.find()) {
                    if (mProgressBarHorizontal.getProgress() < 100) {
                        mProgressBarHorizontal.incrementProgressBy(1);
                    }
                    english = m.group(1);
                    chinese = m.group(2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(english);
                            textView1.setText(chinese);
                        }
                    });
                    String json = HttpConnection.request("http://dict-mobile.iciba.com/interface/index.php?c=word&list=&client=1&timestamp=1535635907&sign=c0aa236ef3427a6f&uuid=f0aaa58de60b40e4bfe6cd19b5edabfd&sv=android8.0.0&v=10.0.1&uid=&identity=2&dic_id=0", "word=" + english);
                    //Log.e("mp3", " mp3 = " + json);
                    if (json.contains("3011")) {
                        checkTtsAndSpeak("单词错误");
                    } else {
                        getWordUrl(json);
                    }
                }
            }
        }).start();

    }

    private void getWordUrl(String json) {
        try {
            Pattern pattern = Pattern.compile("ph_en_mp3\":\"(.+?\\.mp3)");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                //Log.e("mp3url", " mp3 = " + matcher.group(1).replaceAll("\\\\", ""));
                play(matcher.group(1).replaceAll("\\\\", ""));
            } else {
                pattern = Pattern.compile("ph_am_mp3\":\"(.+?\\.mp3)");
                matcher = pattern.matcher(json);
                if (matcher.find()) {
                    //Log.e("mp3url", " mp3 = " + matcher.group(1).replaceAll("\\\\", ""));
                    play(matcher.group(1).replaceAll("\\\\", ""));
                } else {
                    pattern = Pattern.compile("ph_tts_mp3\":\"(.+?\\.mp3)");
                    matcher = pattern.matcher(json);
                    if (matcher.find()) {
                        //Log.e("mp3url", " mp3 = " + matcher.group(1).replaceAll("\\\\", ""));
                        play(matcher.group(1).replaceAll("\\\\", ""));
                    } else {
                        beginSpeech();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Log.e("mp3", json);
        }
    }

    //控制播放两次单词
    int count = 0;

    public void play(String filepath) {
        if (filepath.equals("network_error")) {
            Toast.makeText(getApplicationContext(), R.string.network_not_work, Toast.LENGTH_SHORT).show();
            return;
        }
        if (filepath.startsWith("http://")) {
            try {
                mediaPlayer.setDataSource(filepath);//设置播放的数据源。
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //mediaPlayer.prepare();//同步的准备方法。
                mediaPlayer.prepareAsync();//异步的准备
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        count++;
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (count < times) {
                            mediaPlayer.start();
                            count++;
                        } else if (count >= times) {
                            count = 0;
                            mediaPlayer.reset();
                            if (isReadChinese) {
                                checkTtsAndSpeak(chinese);
                            } else {
                                checkTtsAndSpeak(" ");
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                //Log.e("player", "播放失败");
                checkTtsAndSpeak("单词播放失败");
            }
        } else {
            //Log.e("player", "路径不对");
            getWordUrl(filepath);
        }
    }

    /**
     * 检查TTS是否正常
     */
    private void checkTtsAndSpeak(String content) {
        if (textToSpeech == null) {
            initTts();
        }
        textToSpeech.speak(content,
                TextToSpeech.QUEUE_FLUSH, ttsOptions);
    }


    //双击返回退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            isExit = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        mAudioManager.registerMediaButtonEventReceiver(mComponentName);
        registerReceiver(headSetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 取消注册
        mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
        unregisterReceiver(headSetReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null)
            textToSpeech.shutdown();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mAudioManager = null;
        mComponentName = null;
        super.onDestroy();
    }

    private final BroadcastReceiver headSetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
                // phone headset plugged
                if (intent.getIntExtra("state", 0) == 1) {
                    // do something
//					Log.d(TAG, "耳机检测：插入");
//					Toast.makeText(context, "耳机检测：插入", Toast.LENGTH_SHORT) .show();
                    mAudioManager.registerMediaButtonEventReceiver(mComponentName);
                    // phone head unplugged
                } else {
                    // do something
//					Log.d(TAG, "耳机检测：没有插入");
//					Toast.makeText(context, "耳机检测：没有插入", Toast.LENGTH_SHORT).show();
                    if (isPlaying) {
                        pauseOrPlay();
                        isPlaying = !isPlaying;
                    }
                    mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
                }
            }
        }
    };

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("ok"))
                pauseOrPlay();
            else if (action.equals("restart")) {
                //Log.e("reajfkaej","重启");
                mProgressBarHorizontal.setProgress(0);
                m = null;
                m = p.matcher(words);
                if (!isPlaying) {
                    pauseOrPlay();
                }
            }
        }

    }
}