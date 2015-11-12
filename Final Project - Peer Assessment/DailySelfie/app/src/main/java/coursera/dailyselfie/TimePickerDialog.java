package coursera.dailyselfie;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerDialog extends Dialog {
    private Switch notifSwitch;
    private TimePicker timePicker;
    private Button saveBtn;
    private Calendar time;
    private boolean isActive = false;

    public TimePickerDialog (Context context) {
        super (context);
    }

    public void setTime (boolean isActive, Calendar time) {
        this.time = time;
        this.isActive = isActive;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.timepickerdialog);
        notifSwitch = (Switch) findViewById (R.id.notifswitcher);
        timePicker = (TimePicker) findViewById (R.id.timepicker);
        timePicker.setIs24HourView (true);
        saveBtn = (Button) findViewById (R.id.saveBtn);
        if (time != null) {
            timePicker.setCurrentHour (time.get (Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute (time.get (Calendar.MINUTE));
        } else {
            Calendar defTime = Calendar.getInstance ();
            timePicker.setCurrentHour (defTime.get (Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute (defTime.get (Calendar.MINUTE));
        }
        notifSwitch.setChecked (isActive);
        saveBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                if (notifSwitch.isChecked ()) {
                    isActive = true;
                    int hour = timePicker.getCurrentHour ();
                    int minute = timePicker.getCurrentMinute ();
                    time = Calendar.getInstance ();
                    time.set (Calendar.HOUR_OF_DAY, hour);
                    time.set (Calendar.MINUTE, minute);
                    time.set (Calendar.SECOND, 0);
                } else {
                    isActive = false;
                }
                TimePickerDialog.this.dismiss ();
            }
        });
    }

    public boolean isNotificationActive () {
        return isActive;
    }

    public Calendar getTime () {
        return time;
    }
}
