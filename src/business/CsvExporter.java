package business;

import data.Tickets;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter implements Exporter {
    @Override
    public void export(List<Tickets> tickets, String fileName) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            String[] header = {"Ticket ID", "Concert Name", "Price", "Buyer Name", "Cashier ID"};
            writer.writeNext(header);
            for (Tickets ticket : tickets) {
                String[] row = {
                        Integer.toString(ticket.getTicketId()),
                        Integer.toString(ticket.getConcertId()),
                        Float.toString(ticket.getTicketPrice()),
                        ticket.getUserName(),
                        Integer.toString(ticket.getUserId())
                };
                writer.writeNext(row);
            }
        }
    }
}
