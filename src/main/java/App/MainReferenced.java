package App;

import DAO.Referenced.ColecaoDAO;
import DAO.Referenced.EstampaDAO;
import DAO.Referenced.PecaDAO;
import DAO.Referenced.EstampaPecaDAO;

public class MainReferenced {
    public static void main(String[] args) {
        ColecaoDAO colecaoDAO = new ColecaoDAO();
        colecaoDAO.inserirColecoes();

        EstampaDAO estampaDAO = new EstampaDAO();
        estampaDAO.inserirEstampas();

        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.inserirPecas();

        EstampaPecaDAO estampaPecaDAO = new EstampaPecaDAO();
        estampaPecaDAO.inserirEstampaPeca();
    }
}
