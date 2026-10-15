package fr.quentinlecompte.pharmasearch.medicament;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class BdpmImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BdpmImporter.class);
    private static final int BATCH_SIZE = 1000;

    private final MedicamentRepository repository;
    private final Path file;

    public BdpmImporter(MedicamentRepository repository, @Value("${pharmasearch.import.file}") String file) {
        this.repository = repository;
        this.file = Path.of(file);
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            log.info("Table medicament déjà remplie, import ignoré");
            return;
        }
        if (!Files.exists(file)) {
            log.warn("Fichier {} absent, import ignoré", file);
            return;
        }

        List<Medicament> batch = new ArrayList<>(BATCH_SIZE);
        long total = 0;
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] c = line.split("\t", -1);
                if (c.length < 11) continue;
                batch.add(new Medicament(c[0].trim(), c[1].trim(), c[2].trim(), c[3].trim(),
                        c[4].trim(), c[6].trim(), c[10].trim()));
                if (batch.size() == BATCH_SIZE) {
                    repository.saveAll(batch);
                    total += batch.size();
                    batch.clear();
                }
            }
            repository.saveAll(batch);
            total += batch.size();
        }
        log.info("Import terminé : {} médicaments", total);
    }
}
