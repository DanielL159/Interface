package application;

import entities.CarRental;
import entities.Vehicle;
import modelServicies.BrasilTaxService;
import modelServicies.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel: ");
        System.out.println("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
        System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);

        CarRental cr= new CarRental(start,finish,new Vehicle(carModel));

        RentalService rentalService = new RentalService(new BrasilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("Fatura: ");
        System.out.println("Pagamento Basico: " + String.format("%.2f",cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("Total a pagar: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
        sc.close();
    }
}