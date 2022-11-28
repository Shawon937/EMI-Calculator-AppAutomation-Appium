package dataset;

import org.testng.annotations.DataProvider;

public class Dataset {
    @DataProvider(name = "data-provider")
    public Object[][] setData(){
        return new Object[][] {
                {100000,9.0,2, 2.0, 4568, 9643, 2000, 109643},
                {325000,9.5,5,1.5,6826,84536,4875,409536},
                {200000,10,5,1.5,4249,54965,3000,254965},
                {30000,4,7,1.1,410,4445,330,34445},
                {50000,9.5,5,1.5,1050,13006,750,63006}

        };
    }
}
