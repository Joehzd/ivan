package com.mx.object;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void init() {
        System.out.println("我在初始化");
        Person person = new Person();
        person.setName("藏三");
        person.setNumber(222);
        person.setSex("男");
        Field[] fields = person.getClass().getDeclaredFields();
        System.out.println(fields[0] + "---" + fields[1]);

        try {
            Field field = person.getClass().getField("name");
            //私有变量必须先设置Accessible为true
            field.setAccessible(true);

            field.set(person, "李四");
            System.out.println(field.get(person));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    TextView textView;

    int src[] = new int[]{3, 5, 1, 7, 3, 9, 8, 4, 5, 11, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();

        // 1.使用new 构造对象
        ObjectGenerate objectGenerate1 = new ObjectGenerate();
        System.out.println("1.new 对象" + "\t" + objectGenerate1.hashCode());
        try {

            // 2.使用反射 构造对象
            ObjectGenerate objectGenerate3 = ObjectGenerate.class.newInstance();
            System.out.println("2.反射 对象" + "\t" + objectGenerate3.hashCode());

            // 3.使用反射Constructor 构造对象
            Constructor<ObjectGenerate> objectGenerateConstructor = ObjectGenerate.class.getConstructor();
            ObjectGenerate objectGenerate4 = objectGenerateConstructor.newInstance();
            System.out.println("3.反射Constructor 对象" + "\t" + objectGenerate4.hashCode());



            // 4使用clone 构造对象
            ObjectGenerate objectGenerate2 = (ObjectGenerate) objectGenerate1.clone();
            System.out.println("4.clone objectGenerate1 对象" + "\t" + objectGenerate2.hashCode());

            // 5.使用反序列化 构造对象
            // 此处不能使用FileOutputStream而要使用openFileOutput读写文件
            ObjectOutputStream outputStream = new ObjectOutputStream(openFileOutput("obj.txt", MODE_PRIVATE));
            outputStream.writeObject(objectGenerate1);
            outputStream.close();
            ObjectInputStream inputStream = new ObjectInputStream(openFileInput("obj.txt"));
            ObjectGenerate objectGenerate5 = (ObjectGenerate) inputStream.readObject();
            inputStream.close();
            System.out.println("5.反序列化 对象" + "\t" + objectGenerate5.hashCode());


        } catch (CloneNotSupportedException | InstantiationException
                | IllegalAccessException | ClassNotFoundException
                | InvocationTargetException | IOException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        textView = (TextView) findViewById(R.id.textView);
    }

    public void click(View view) {
        textView.setText("click");
        maoPao(src);
    }


    public void maoPao(int[] src) {
        for (int out = src.length - 1; out > 1; out--) {
            for (int b = 0; b < out; b++) {
                if (src[b] > src[out]) {
                    swap(out, b);
                }
            }
        }
        printSrc();
    }

    private void swap(int out, int b) {
        int temp = src[out];
        src[out] = src[b];
        src[b] = temp;

    }

    public void printSrc() {
        for (int num : src) {
            System.out.println("-" + num);
        }
    }
}
