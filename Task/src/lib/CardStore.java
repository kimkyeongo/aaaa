package lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CardStore {
	
	private final String fileName = "./store/NameCardBook.txt";
	
	public boolean chkStore () {
		boolean chker = false;
		
		File mkFile = new File("./store/NameCardBook.txt");
		File mkDir = new File("./store");
		
		try {
			if (!mkFile.exists()) {
				if (!mkDir.exists()) {
					mkDir.mkdir();
				}
				mkFile.createNewFile();
			}
			chker = true;
		} catch (IOException ioe) {
			System.out.println("[!] 파일/경로 생성 시 문제가 발생하였습니다.");
		}
		return chker;
	}
	
	@SuppressWarnings("resource")
	public void loadBook (HashMap<Integer, Person> cards) throws IOException {
		if (chkStore()) {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			InputCard ic = new InputCard();
			
			String rLine = "";
			String [] inArr = new String[6];
			int chk = 1;
			
			while ((rLine = reader.readLine()) != null) {
				try {
					inArr = (rLine.trim()).split("\\|");
					ic.add(cards, inArr);
				} catch (Exception e) {
			System.out.println("[!] 파일 내 "+ chk++ +" 번 라인에 문제가 있습니다.");
				}
			}
		}
	}
	
	public void storeCard (int num, Person p) {
		storeCard(num, p, true);
	}
	
	public void storeCard (int num, Person p, boolean chker) {
		BufferedWriter writer = null;
		String data = num+"|"+p.getName()+"|"+p.getTel()+"|"+p.getGroup()+"|"+p.getTitle()+"|"+p.getIntSex();
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName, chker));
			writer.append(data+"\n");
		} catch (Exception e) {
			System.out.println("[!] 저장 중에 문제가 발생하였습니다.");
			System.out.println("[!] DATA : "+data);
//			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
