import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name ="player_table")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "player_name")
	private String name;
	
	@Column(name = "player_age")
	private int age;
	
	@Column(name = "player_team_name")
	private String teamName;
	
	
	public Player() {
		
	}
	
	
	
	
	@Override
	public String toString() {
		return "Player [Id=" + Id + ", name=" + name + ", age=" 
				+ age + ", teamName=" + teamName + "]";
	}




	public Player(Long id, String name, int age, String teamName) {
		super();
		Id = id;
		this.name = name;
		this.age = age;
		this.teamName = teamName;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
	


}
