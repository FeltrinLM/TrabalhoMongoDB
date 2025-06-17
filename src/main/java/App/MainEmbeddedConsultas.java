package App;

import DAO.Embedded.ConsultaEmbeddedDAO;

public class MainEmbeddedConsultas {
    public static void main(String[] args) {
        ConsultaEmbeddedDAO dao = new ConsultaEmbeddedDAO();

        System.out.println("\n🔎 Consulta 1: Listar o nome da coleção e as peças com quantidade menor que 30\n");
        dao.consulta1_PecasComQuantidadeMenorQue30();

        System.out.println("\n🔎 Consulta 2: Listar o nome da coleção e das estampas que usam a peça 'Calça'\n");
        dao.consulta2_EstampasComPecaCalça();
    }
}

