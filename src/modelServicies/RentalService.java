package modelServicies;

import entities.CarRental;
import entities.Invoice;

import java.time.Duration;

public class RentalService {
    private  Double pricePerHour = 10.0;
    private  Double pricePerDay = 130.0;

    private final TaxService taxService;

    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    public RentalService(TaxService taxService) {
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental){

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();	
        double hour = minutes / 60.0 ;

        double basicPayment;
        if (hour <= 12.0){
           basicPayment = pricePerHour * Math.ceil(hour);
        }else {
            basicPayment = pricePerDay * Math.ceil(hour / 24);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment,tax));
        
    }
}

