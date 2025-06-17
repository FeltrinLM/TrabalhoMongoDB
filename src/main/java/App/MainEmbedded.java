package App;

import DAO.Embedded.DBEmbeddedDAO;

public class MainEmbedded {
    public static void main(String[] args) {
        DBEmbeddedDAO dao = new DBEmbeddedDAO();
        dao.inserirColecaoFromJson();
    }
}

