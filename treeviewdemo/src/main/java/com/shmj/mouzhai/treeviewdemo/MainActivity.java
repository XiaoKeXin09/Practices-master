package com.shmj.mouzhai.treeviewdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.shmj.mouzhai.treeviewdemo.adapter.SimpleTreeListViewAdapter;
import com.shmj.mouzhai.treeviewdemo.adapter.TreeListViewAdapter;
import com.shmj.mouzhai.treeviewdemo.bean.FileBean;
import com.shmj.mouzhai.treeviewdemo.utils.Node;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mTree;
    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTree = (ListView) findViewById(R.id.lv_tree_view);

        initDatas();

        try {
            mAdapter = new SimpleTreeListViewAdapter<FileBean>(this, mTree, mDatas, 0);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        initEvent();
    }

    private void initEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    Toast.makeText(MainActivity.this, node.getName(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTree.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position,
                                           long id) {
                final EditText et = new EditText(MainActivity.this);
                new android.app.AlertDialog.Builder(MainActivity.this).setTitle("Add Node")
                        .setView(et)
                        .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(et.getText().toString()))
                                    return;
                                mAdapter.addExtraNode(position, et.getText()
                                        .toString());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return true;
            }
        });
    }

    /**
     * 设置数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        FileBean fileBean = new FileBean(1, 0, "根目录1");
        mDatas.add(fileBean);
        fileBean = new FileBean(2, 0, "根目录2");
        mDatas.add(fileBean);
        fileBean = new FileBean(3, 0, "根目录3");
        mDatas.add(fileBean);
        fileBean = new FileBean(4, 1, "根目录1-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(5, 1, "根目录1-2");
        mDatas.add(fileBean);
        fileBean = new FileBean(6, 5, "根目录1-1-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(7, 3, "根目录3-1");
        mDatas.add(fileBean);
        fileBean = new FileBean(8, 3, "根目录3-2");
        mDatas.add(fileBean);
    }
}
