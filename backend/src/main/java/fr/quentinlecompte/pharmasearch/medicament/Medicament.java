package fr.quentinlecompte.pharmasearch.medicament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    private String cis;

    @Column(nullable = false)
    private String denomination;

    private String forme;
    private String voiesAdministration;
    private String statutAmm;
    private String etatCommercialisation;
    private String titulaire;

    protected Medicament() {
    }

    public Medicament(String cis, String denomination, String forme, String voiesAdministration,
                      String statutAmm, String etatCommercialisation, String titulaire) {
        this.cis = cis;
        this.denomination = denomination;
        this.forme = forme;
        this.voiesAdministration = voiesAdministration;
        this.statutAmm = statutAmm;
        this.etatCommercialisation = etatCommercialisation;
        this.titulaire = titulaire;
    }

    public String getCis() { return cis; }
    public String getDenomination() { return denomination; }
    public String getForme() { return forme; }
    public String getVoiesAdministration() { return voiesAdministration; }
    public String getStatutAmm() { return statutAmm; }
    public String getEtatCommercialisation() { return etatCommercialisation; }
    public String getTitulaire() { return titulaire; }
}
