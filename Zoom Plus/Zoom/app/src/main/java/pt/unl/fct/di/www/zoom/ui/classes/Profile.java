package pt.unl.fct.di.www.zoom.ui.classes;

public class Profile {

    public String name;
    public String username;
    public String location;
    public String sex;

    public Profile(String name, String username, String location, boolean aux) {
        this.name = name;
        this.username = username;
        this.location = location;
        this.sex = null;

    }

    public Profile(String name, String username, String location, String sex) {
        this.name = name;
        this.username = username;
        this.location = location;
        this.sex = sex;

    }

    public Profile(String name, String username, String sex) {
        this.name = name;
        this.username = username;
        this.sex = sex;
        this.location = null;

    }

    public Profile(String name, String username) {
        this.name = name;
        this.username = username;
        this.sex = null;
        this.location = null;

    }

    public void edit (String name, String location) {
        if (name != null || !name.equals(""))
            this.name = name;
        if (location != null || !location.equals(""))
            this.location = location;

    }

    public void sex (String sex) {
        this.sex = sex;
    }
}
