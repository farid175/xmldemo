package az.itcity.generator;

import az.itcity.domain.Isci;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Isci> getIsciList() {

        // todo refactor do db
        List<Isci> isciler = new ArrayList<>();
        isciler.add(new Isci(1, "Ali", "Mammadov", "ali@gmail.com", "IT_PROG", BigDecimal.valueOf(3000), 60));
        isciler.add(new Isci(2, "Rza", "Rzayev", "rza.rzayev@gmail.com", "IT_PROG", BigDecimal.valueOf(5000), 60));
        isciler.add(new Isci(3, "Tural", "Muradbeyli", "tural.muradbeyli@gmail.com", "IT_PROG", BigDecimal.valueOf(4500), 60));
        isciler.add(new Isci(4, "Ilkin", "Ashrafli", "nikli@gmail.com", "IT_PROG", BigDecimal.valueOf(3500), 60));
        isciler.add(new Isci(5, "Ali", "Gojayev", "ali.gojayev@gmail.com", "AD_VP", BigDecimal.valueOf(13500), 90));

        return isciler;
    }
}
