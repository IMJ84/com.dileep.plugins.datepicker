package com.dileep.plugins.datepicker;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class DateTimePickerDialog extends Dialog implements OnClickListener {

	DatePicker datePicker;
	TimePicker timePicker;
	OnDateTimeSetListener listener;

	public DateTimePickerDialog(Context context, OnDateTimeSetListener listener) {
		super(context);
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Context context = getContext();

		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		datePicker = new DatePicker(context);
		ll.addView(datePicker);
		timePicker = new TimePicker(context);
		timePicker.setIs24HourView(Boolean.FALSE);
		ll.addView(timePicker);
		LinearLayout buttons = new LinearLayout(context);
		buttons.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

		Button cancel = new Button(context);
		cancel.setText("Cancel");
		cancel.setOnClickListener(new CancelOnClickListener(this));
		cancel.setLayoutParams(layoutParams);
		buttons.addView(cancel);
		Button set = new Button(context);
		set.setText("Set");
		set.setOnClickListener(this);
		set.setLayoutParams(layoutParams);
		buttons.addView(set);

		ll.addView(buttons);

		setCancelable(true);
		setCanceledOnTouchOutside(true);
		setTitle("Set Date and Time");
		setContentView(ll);

	}

	@Override
	public void onClick(View v) {

		listener.onDateTimeSet(this, //
				datePicker.getYear(), //
				datePicker.getMonth(), //
				datePicker.getDayOfMonth(), //
				timePicker.getCurrentHour(), //
				timePicker.getCurrentMinute() //
		);

		dismiss();
	}
	
	public void setDefaultDate(int year, int month, int day, int hour, int minute) {
		datePicker.updateDate(year, month, day);
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
	}

	static class CancelOnClickListener implements
			android.view.View.OnClickListener {

		private Dialog dialog;

		private CancelOnClickListener(Dialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void onClick(View v) {
			dialog.cancel();

		}

	}

}
