package jpabook.model.entity;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @Column(name = "PLAYER_ID")
    private String id;

    private String userName;

    @OneToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Player(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Player() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", team=" + team +
                '}';
    }
}
