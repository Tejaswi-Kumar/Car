
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tejpa
 */
public class scorecompare implements Comparator<car>{
    @Override
    public int compare(car comparecar1,car comparecar2) {
		if(comparecar1.getscore()==comparecar2.getscore())
                    return 0;
                else if(comparecar1.getscore()>comparecar2.getscore())
                    return -1;
                else
                    return 1;

	}
}
