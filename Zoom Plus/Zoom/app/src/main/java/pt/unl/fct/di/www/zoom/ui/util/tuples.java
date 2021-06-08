package pt.unl.fct.di.www.zoom.ui.util;

import android.widget.TextView;

import java.util.List;

public class tuples {

    public int color;
    public List<TextView> tvs;

    public tuples(int color, List<TextView> tvs) {
        this.color = color;
        this.tvs = tvs;
    }
}
