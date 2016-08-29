package kodefoundry.com.criminalintent.model;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID id;

    private String title;

    private Date date;

    private boolean solved;

    public Crime() {
        id = UUID.randomUUID();
        setDate(new Date());
    }

    public Crime setTitle(String title) {
        this.title = title;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return DateFormat.format("E, MMMM d, yyyy", getDate()).toString();
    }

    public boolean isSolved() {
        return solved;
    }

    public Crime setDate(Date date) {
        this.date = date;
        return this;
    }

    public Crime setSolved(boolean solved) {
        this.solved = solved;
        return this;
    }
}
