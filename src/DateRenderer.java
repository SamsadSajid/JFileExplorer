import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;

/**
 * Created by shamsad on 4/15/17.
 * dd MMM yyyy format for the JTable.
 */
public class DateRenderer extends DefaultTableCellRenderer {

    private SimpleDateFormat formatter;

    public DateRenderer() {
        String pattern = "dd MMM yyyy";
        this.formatter = new SimpleDateFormat(pattern);
    }

    @Override
    protected void setValue(Object value) {
        setText((value == null) ? "" : formatter.format(value));
    }
}
