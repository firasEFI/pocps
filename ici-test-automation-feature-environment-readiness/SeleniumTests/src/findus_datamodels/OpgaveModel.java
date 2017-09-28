package findus_datamodels;

import java.time.LocalDateTime;

public class OpgaveModel {

    private String opgaveId;
    private String opgaveKategori;
    private String OpgaveType;
    private String prioritet;
    private String beskrivelse;
    private String rolle;
    private String tildelt;
    private LocalDateTime sagsbehandlerOprettet;
    private LocalDateTime sagsbehandlerAfsluttet;
    private LocalDateTime oprettelsesDato;
    private LocalDateTime afsluttetDato;
    private String statusPaaOpgaven;
    private LocalDateTime tidligsteTidsfrist;
    private LocalDateTime senesteTidsfrist;
    private String relateretObjekter;

    public String getOpgaveId() {
        return opgaveId;
    }

    public void setOpgaveId(String opgaveId) {
        this.opgaveId = opgaveId;
    }

    public String getOpgaveKategori() {
        return opgaveKategori;
    }

    public void setOpgaveKategori(String opgaveKategori) {
        this.opgaveKategori = opgaveKategori;
    }

    public String getOpgaveType() {
        return OpgaveType;
    }

    public void setOpgaveType(String opgaveType) {
        OpgaveType = opgaveType;
    }

    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getTildelt() {
        return tildelt;
    }

    public void setTildelt(String tildelt) {
        this.tildelt = tildelt;
    }

    public LocalDateTime getSagsbehandlerOprettet() {
        return sagsbehandlerOprettet;
    }

    public void setSagsbehandlerOprettet(LocalDateTime sagsbehandlerOprettet) {
        this.sagsbehandlerOprettet = sagsbehandlerOprettet;
    }

    public LocalDateTime getSagsbehandlerAfsluttet() {
        return sagsbehandlerAfsluttet;
    }

    public void setSagsbehandlerAfsluttet(LocalDateTime sagsbehandlerAfsluttet) {
        this.sagsbehandlerAfsluttet = sagsbehandlerAfsluttet;
    }

    public LocalDateTime getOprettelsesDato() {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDateTime oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
    }

    public LocalDateTime getAfsluttetDato() {
        return afsluttetDato;
    }

    public void setAfsluttetDato(LocalDateTime afsluttetDato) {
        this.afsluttetDato = afsluttetDato;
    }

    public String getStatusPaaOpgaven() {
        return statusPaaOpgaven;
    }

    public void setStatusPaaOpgaven(String statusPaaOpgaven) {
        this.statusPaaOpgaven = statusPaaOpgaven;
    }

    public LocalDateTime getTidligsteTidsfrist() {
        return tidligsteTidsfrist;
    }

    public void setTidligsteTidsfrist(LocalDateTime tidligsteTidsfrist) {
        this.tidligsteTidsfrist = tidligsteTidsfrist;
    }

    public LocalDateTime getSenesteTidsfrist() {
        return senesteTidsfrist;
    }

    public void setSenesteTidsfrist(LocalDateTime senesteTidsfrist) {
        this.senesteTidsfrist = senesteTidsfrist;
    }

    public String getRelateretObjekter() {
        return relateretObjekter;
    }

    public void setRelateretObjekter(String relateretObjekter) {
        this.relateretObjekter = relateretObjekter;
    }

    public void test() {

    }
}
