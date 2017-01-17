package init;


/**
 * Class used to store component to create filter
 * Created by titanium on 10/01/2017.
 */
public class StoredComponent {
    private String property;
    private String value;
    private String format;

    public StoredComponent(String prop, String val, String format){
        this.property = prop;
        this.value = val;
        this.format = format;
    }

    // getter and setter

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
