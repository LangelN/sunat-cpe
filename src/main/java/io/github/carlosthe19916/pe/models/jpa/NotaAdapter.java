package io.github.carlosthe19916.pe.models.jpa;

import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.jpa.JpaModel;
import io.github.carlosthe19916.core.models.jpa.OrganizationAdapter;
import io.github.carlosthe19916.pe.models.*;
import io.github.carlosthe19916.pe.models.jpa.entities.NotaEntity;

import javax.persistence.EntityManager;

public class NotaAdapter implements NotaModel, JpaModel<NotaEntity> {

    private final EntityManager em;
    private final NotaEntity nota;

    protected NotaAdapter(EntityManager em, NotaEntity nota) {
        this.em = em;
        this.nota = nota;
    }

    public static NotaEntity toEntity(NotaModel model, EntityManager em) {
        if (model instanceof NotaAdapter) {
            return ((NotaAdapter) model).getEntity();
        }
        return em.getReference(NotaEntity.class, model.getId());
    }

    @Override
    public NotaEntity getEntity() {
        return nota;
    }

    @Override
    public String getId() {
        return nota.getId();
    }

    @Override
    public String getSerie() {
        return nota.getSerie();
    }

    @Override
    public int getNumero() {
        return nota.getNumero();
    }

    @Override
    public String getCodigoTipoComprobante() {
        return nota.getCodigoTipoComprobante();
    }

    @Override
    public String getCodigoMotivo() {
        return nota.getTipoNota();
    }

    @Override
    public void setCodigoMotivo(String tipoNota) {
        nota.setTipoNota(tipoNota);
    }

    @Override
    public InvoiceModel getInvoiceAfectado() {
        return new InvoiceAdapter(em, nota.getInvoiceAfectado());
    }

    @Override
    public EstadoComprobantePago getEstado() {
        return nota.getEstado();
    }

    @Override
    public void setEstado(EstadoComprobantePago estado) {
        nota.setEstado(estado);
    }

    @Override
    public boolean getEnviarSunat() {
        return nota.isEnviarSunat();
    }

    @Override
    public void setEnviarSunat(boolean enviarSunat) {
        nota.setEnviarSunat(enviarSunat);
    }

    @Override
    public boolean getEnviarCliente() {
        return nota.isEnviarCliente();
    }

    @Override
    public void setEnviarCliente(boolean enviarCliente) {
        nota.setEnviarCliente(enviarCliente);
    }

    @Override
    public String getFileId() {
        return nota.getFileId();
    }

    @Override
    public void setFileId(String fileId) {
        nota.setFileId(fileId);
    }

    @Override
    public CdrModel getCdr() {
        return new CdrAdapter(nota.getCdr());
    }

    @Override
    public DatosVentaModel getDatosVenta() {
        return new DatosVentaAdapter(em, nota.getDatosVenta());
    }

    @Override
    public EstadoSunatModel getEstadoSunat() {
        return new EstadoSunatAdapter(nota.getEstadoSunat());
    }

    @Override
    public OrganizationModel getOrganization() {
        return new OrganizationAdapter(nota.getOrganization());
    }

}
