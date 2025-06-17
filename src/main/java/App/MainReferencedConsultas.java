package App;

import DAO.Referenced.ConsultaReferencedDAO;

public class MainReferencedConsultas {
    public static void main(String[] args) {
        ConsultaReferencedDAO dao = new ConsultaReferencedDAO();

        System.out.println("==== CONSULTAS - VERSÃO REFERENCED ====");

        dao.consulta1_EstampasComPecasAbaixoDe30();

        dao.consulta2_EstampasPorColecao();
    }
}
