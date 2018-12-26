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

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            //TODO 비즈니스 로직
            Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            Player player1 = new Player("player1", "회원1");
            em.persist(player1);
            team1.add(player1);

            Player player2 = new Player("player2", "회원2");
            em.persist(player2);
            team1.add(player2);

            Team team = em.find(Team.class, "team1");
            List<Player> players = team.getPlayers();


            System.out.println("사이즈 : " + players.size());
            for (Player player : players) {
                System.out.println("player : " + player.getUserName());
            }

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료

    }


}
