/**
 * @file KNDCalcActivity.java
 * 
 * @brief A-A Calculator(Let's go dutch!)
 * 
 * @detail Total amount and individual expense should be only in integer.
 * 
 * @author 白峰(baifeng)
 * @date May 8~9, 2012
 * 
 * @version 0.1	Prototype functions.
 * @version 0.1.1 Fixes result display format.
 */

package com.knd.calc;

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
	
	private EditText tv_Total;		///< total amount
	private EditText tv_Persons;	///< number of persons
	private TextView tv_Result;		///< payment method
	private Button bt_calc;			///< calculation button
	
	private int total;		///< total 
	private int persons;	///< number of persons
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init_views();
    }
    
    /**
     * Initialize all views.
     */
    private void init_views() {
        
        bt_calc = (Button)findViewById(R.id.buttonCalc);
        tv_Total = (EditText)findViewById(R.id.editTextTotal);
        tv_Persons = (EditText)findViewById(R.id.editTextPersons);
        tv_Result = (TextView)findViewById(R.id.textViewResult);    	
    	        
        //TODO Refactor this function.
        bt_calc.setOnClickListener(new Button.OnClickListener()	{
        	private String result;	 	///< Payment method string.        	
        	private int trialResult;	///< trial result, to test.
        	private int p1;	///< first part, in person
        	private int p2;	///< second part, in person
        	private int pay1;	///< first part, in cash unit.
        	private int pay2;	///< second part, used when total could not been divided integrally. 
        	
        	/**	"onClick" Callback method.
        	 * 
        	 * @todo TODO: Made all the Chinese strings in xml file.
        	 */
			public void onClick(View arg0) {
				int total2; ///< Temporally use
				String temp_res = new String("");
				
				// once click button, lose focuses of both EditTexts.
				//TODO Hide IME here.
				tv_Total.clearFocus();
				tv_Persons.clearFocus();
				
				result = getResources().getString(R.string.result);

		    	try {
			        total = Integer.parseInt(tv_Total.getText().toString());
			        persons = Integer.parseInt(tv_Persons.getText().toString());
		    	}
		    	catch (Exception e) {
		    		if (total==0 || persons==0) {
		    			temp_res = new String("还有地方没输入数值呢！");
		    		}
		    	}	
		    	
				try {
					trialResult = total / persons;
				}
				catch(Exception e) {
					// divided by zero error.
					if (persons==0) {
						temp_res = new String("见鬼了！你确定人数为0？");
					}
				}
				
				if (temp_res.isEmpty()) {
					total2 = trialResult * persons;
					if (total2 != total) {
						p2 = total - total2;
						pay2 = trialResult + 1;
						p1 = persons - p2;
						pay1 = trialResult;		
						
						result += new String("\t" + p1 + " 个人刷：" + pay1 +"块；\n");
						result += new String("\t" + p2 + " 个人刷：" + pay2 +"块。\n");
					}
					else {	// easy A-A method.
						result += new String("每人刷：" + trialResult + "块。");
					}
				}
				else {
					result = temp_res;
				}
				
				tv_Result.setText(result);
				
			}
        	
        } );

    }
    
}
