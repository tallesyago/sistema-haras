package com.haras.model;

import java.time.LocalDate;

public class ConsultaVeterinaria {
    private String id;
    private Equino cavalo;
    private LocalDate dataConsulta;
    private String tipoConsulta;
    private double pesoAtual;
    private String temperatura;
    private String pressaoArterial;
    private String sintomasRelatados;
    private String diagnostico;
    private String tratamentoPrescrito;
    private String observacoesAdicionais;
    private LocalDate proximaConsulta;

    public ConsultaVeterinaria() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Equino getCavalo() { return cavalo; }
    public void setCavalo(Equino cavalo) { this.cavalo = cavalo; }

    public LocalDate getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }

    public String getTipoConsulta() { return tipoConsulta; }
    public void setTipoConsulta(String tipoConsulta) { this.tipoConsulta = tipoConsulta; }

    public double getPesoAtual() { return pesoAtual; }
    public void setPesoAtual(double pesoAtual) { this.pesoAtual = pesoAtual; }

    public String getTemperatura() { return temperatura; }
    public void setTemperatura(String temperatura) { this.temperatura = temperatura; }

    public String getPressaoArterial() { return pressaoArterial; }
    public void setPressaoArterial(String pressaoArterial) { this.pressaoArterial = pressaoArterial; }

    public String getSintomasRelatados() { return sintomasRelatados; }
    public void setSintomasRelatados(String sintomasRelatados) { this.sintomasRelatados = sintomasRelatados; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamentoPrescrito() { return tratamentoPrescrito; }
    public void setTratamentoPrescrito(String tratamentoPrescrito) { this.tratamentoPrescrito = tratamentoPrescrito; }

    public String getObservacoesAdicionais() { return observacoesAdicionais; }
    public void setObservacoesAdicionais(String observacoesAdicionais) { this.observacoesAdicionais = observacoesAdicionais; }

    public LocalDate getProximaConsulta() { return proximaConsulta; }
    public void setProximaConsulta(LocalDate proximaConsulta) { this.proximaConsulta = proximaConsulta; }
}
