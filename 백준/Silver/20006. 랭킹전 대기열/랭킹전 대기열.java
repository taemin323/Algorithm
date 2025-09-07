import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 1 <= P <= 300 플레이어의 수
 * 1 <= M <= 300 방의 정원
 * 1 <= L <= 500 플레이어의 레벨
 * N : 닉네임 닉네임의 길이 < 16
 */
public class Main {
	
	static class Player {
		int level;
		String name;
		
		Player(int level, String name) {
			this.level = level;
			this.name = name;
		}
	}
	
	static class Room {
		int firstLevel;
		List<Player> players = new ArrayList<>();
		
		Room(int firstLevel){
			this.firstLevel = firstLevel;
		}
		
		boolean canJoin(Player p, int M) {
			if(players.size() < M && Math.abs(p.level - firstLevel) <= 10) {
				return true;
			}
			return false;
		}
		
		void add(Player p) {
			players.add(p);
		}
		
		boolean isFull(int M) {
			return players.size() == M;
		}

	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int P = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Room> rooms = new ArrayList<>();
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(st.nextToken());
			String nickName = st.nextToken();
			Player cur = new Player(L, nickName);
			
			boolean flag = false;
			for (Room room : rooms) {
				if(room.canJoin(cur, M)) {
					room.add(cur);
					flag = true;
					break;
				}
			}
			if(!flag) {
				Room nr = new Room(L);
				nr.add(cur);
				rooms.add(nr);
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for (Room room : rooms) {
			sb.append(room.isFull(M) ? "Started!\n" : "Waiting!\n");
			room.players.sort(Comparator.comparing(p -> p.name));
			for (Player player : room.players) {
				sb.append(player.level).append(" ").append(player.name).append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}// end of main
}// end of class
