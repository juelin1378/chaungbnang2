package chuangbang.activity;

import cn.bmob.v3.listener.DeleteListener;
import chuangbang.entity.Project;
import chuangbang.util.Final;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

public class MyProDetailsActivity extends Activity implements OnClickListener,Final{
	private ImageView ivLogo;
	private EditText etProName,etProDescription,etProPainPoint;
	private String proName,proDescription,proPainPoint;
	private Project project;

	private Button btDeleted,btUpdate;

	//我的项目object
	private String myProObject;



	private void setView(){
		etProName=(EditText)findViewById(R.id.et_my_pro_detail_title);
		etProDescription=(EditText)findViewById(R.id.et_my_pro_detail_description);
		etProPainPoint=(EditText)findViewById(R.id.et_my_pro_detail_pain_pointer);

		btDeleted=(Button)findViewById(R.id.bnt_activity_my_project_deleted);
		btUpdate=(Button)findViewById(R.id.bnt_activity_my_project_update);

	}
	private void setOnClick(){
		btDeleted.setOnClickListener(this);
		btUpdate.setOnClickListener(this);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_pro_details);

		setView();
		setOnClick();

		Intent intent=getIntent();
		project=(Project) intent.getSerializableExtra("project");
		myProObject=project.getObjectId();
		//把收到的project显示在UI上
		setPro();





	}
	/**
	 * 把传过来的项目详细信息显出出来
	 */
	private void setPro(){
		etProName.setText(project.getName());
		etProDescription.setText(project.getDescription());
		etProPainPoint.setText(project.getPainPointer());
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		//删除按钮
		case R.id.bnt_activity_my_project_deleted:
			new AlertDialog.Builder(MyProDetailsActivity.this).setTitle("警告！").setMessage("你确定删除吗？").
			setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					//执行删除操作
					deleted();

				}
			}).setNegativeButton("取消",null).show();

			break;

			//更新项目按钮
		case R.id.bnt_activity_my_project_update:
				
			break;

		}

	}
	/**
	 * 删除我的项目
	 */
	private void deleted(){
		Project pro=new Project();
		pro.setObjectId(myProObject);
		pro.delete(this,new DeleteListener() {

			@Override
			public void onSuccess() {
				Intent intent=new Intent(INTENT_ACTION_UPDATE_MY_PROJECT);
				sendBroadcast(intent);
				Toast.makeText(MyProDetailsActivity.this, "删除成功", Toast.LENGTH_LONG).show();
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(MyProDetailsActivity.this, arg1, Toast.LENGTH_LONG).show();
				
			}
		});
	}
	/**
	 * 更新操作
	 */
	private void update(){
		
	}






}
