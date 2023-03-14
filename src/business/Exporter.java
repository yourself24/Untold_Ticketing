package business;

import data.Tickets;

import java.io.IOException;
import java.util.List;

public interface Exporter {
    void export(List<Tickets> tickets, String fileName) throws IOException;
}