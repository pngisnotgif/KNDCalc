/**
 * @file KNDCalcActivity.java
 * 
 * @brief A-A���Ѽ�����.
 * 
 * @detail �ܶ�˾�����ֻ��ʹ��������
 * 
 * @author �׷�(baifeng)
 * @date 2012-5-8
 * @version 0.1	Prototype functions.
 */

package com.calc.knd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**	Main Activity.
 * 
 * @author baifeng
 *
 */
public class KNDCalcActivity extends Activity {
	
	private EditText tv_Total;		///< �����ܶ�
	private EditText tv_Persons;	///< ����
	private TextView tv_Result;		///< ֧����ʽ
	private Button bt_calc;			///< ���㰴ť
	
	private int total;		///< total 
	private int persons;	///< number of persons
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        bt_calc = (Button)findViewById(R.id.buttonCalc);
        tv_Total = (EditText)findViewById(R.id.editTextTotal);
        tv_Persons = (EditText)findViewById(R.id.editTextPersons);
        tv_Result = (TextView)findViewById(R.id.textViewResult);
        
        init_views();
    }
    
    /**
     * Initialize all views.
     */
    private void init_views() {
        
        total = Integer.valueOf(tv_Total.getText().toString());
        persons = Integer.valueOf(tv_Persons.getText().toString());
        
        bt_calc.setOnClickListener(new Button.OnClickListener()	{
        	private String result;	///< Payment method string.
        	
        	private int trialResult;	///< trial result, to test.
        	private int p1;	///< first part, in person
        	private int p2;	///< second part, in person
        	private int pay1;	///< first part, in cash unit.
        	private int pay2;	///< second part, used when total could not been divided integrally. 
        	
        	/**	"onClick" Callback method.
        	 * 
        	 */
			public void onClick(View arg0) {

				try {
					trialResult = total / persons;
				}
				catch(Exception e) {
					// divided by zero error.
					
				}
				
				if (trialResult != total) {
					int total2 = trialResult * persons;
					p2 = total - total2;
					pay2 = trialResult + 1;
					p1 = persons - p1;
					pay1 = trialResult;		
					
					result = new String(p1 + " ����ˢ��" + pay1 +"�飻\n");
					result += new String(p2 + " ����ˢ��" + pay2 +"�飻\n");
				}
				else {	// easy A-A method.
					result = new String("ÿ��ˢ��" + trialResult + "�顣");
				}
				
				tv_Result.setText(result);
				
			}
        	
        	}
        );
        
        
        
    }
    
}
