package jpabook.model;


import jpabook.model.entity.Player;
import jpabook.model.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityManager em2 = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            //TODO 비즈니스 로직
            logic(em);

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            // 하지만 여기서 될 수 있었던 것은 DB에서 Foreign Key로 매핑된 정보를 가져오고
            // 여기선 DB에서 가져오는 것이기 때문에 관계가 다 매핑되어 있기 때문입니다.
//            Team team = em2.find(Team.class, "team1");
//            List<Player> players = team.getPlayers();
//
//            System.out.println("사이즈 : " + players.size());
//            for (Player player : players) {
//                System.out.println("player : " + player.getUserName());
//            }

            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료

    }

    private static void logic(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Player player1 = new Player("player1", "회원1");
        player1.setTeam(team1);
        em.persist(player1);

        Player player2 = new Player("player2", "회원2");
        player2.setTeam(team1);
        em.persist(player2);

        // 여기서 안됐었던 이유는 em이 관리하는 영속성 컨텍스트 내에서는 관계를 매핑해주지 않았기 때문입니다.
        Team team = em.find(Team.class, "team1");
        List<Player> players = team.getPlayers();

        System.out.println("사이즈 : " + players.size());
        for (Player player : players) {
            System.out.println("player : " + player.getUserName());
        }
    }


}
