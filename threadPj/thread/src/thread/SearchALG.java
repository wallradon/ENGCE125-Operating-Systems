package thread;
import java.util.ArrayList;
public class SearchALG {

    public String Search(ArrayList<String> arr, String Text) {
//        System.out.println("Working");
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equalsIgnoreCase(Text)) {
                return Text;
            }
        }
        return "";
                
    }

}
