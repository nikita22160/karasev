import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Parser {
    public static void run(){
        try (
                var reader = Files.newBufferedReader(Paths.get("Гранты.csv"));
                var csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                var companyName = nextRecord[0];
                var companyStreet = nextRecord[1];
                var amountStr = nextRecord[2].replace(",", "");
                var amountGrant = amountStr.substring(1);
                var fiscalYear = nextRecord[3];
                var businessType = nextRecord[4];
                var numberOfJobs = nextRecord[5];
//                System.out.println("Название компании : " + companyName);
//                System.out.println("Название улицы : " + companyStreet);
//                System.out.println("Размер гранта : " + amountGrant);
//                System.out.println("Фискальный год : " + fiscalYear);
//                System.out.println("Тип бизнеса : " + businessType);
//                System.out.println("Количество рабочих мест : " + numberOfJobs);

                var company = new Company();
                company.setName(companyName);
                company.setBusinessType(businessType);
                company.setStreet(companyStreet);
                company.setNumberOfJobs(Long.parseLong(numberOfJobs));
                var grant = new Grant(company);
                grant.setAmount(Double.parseDouble(amountGrant));
                grant.setFiscalYear(Integer.parseInt(fiscalYear));
                App.save(grant);
//
//                System.out.println(grant);
//                System.out.println("==========================");
            }
        } catch (CsvValidationException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
