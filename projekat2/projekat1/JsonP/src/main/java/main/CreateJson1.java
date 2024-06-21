package main;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import dataset.Anime;
import dataset.Rang;



public class CreateJson1 {

	public static List<Anime> createJson() {

		try {

			// FileReader filereader = new FileReader("C:\\Users\\User\\Desktop\\projekat
			// nosql" + "\fajl.csv");

			FileReader filereader = new FileReader("C:\\Users\\User\\Desktop\\projekat2\\animes.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

			String[] nextRecord;

			int i = 1;

			List<Anime> listings = new ArrayList<Anime>();

			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("line " + i);
				System.out.println("line length " + nextRecord.length);
				Anime a = new Anime();

				Integer id = Integer.parseInt(nextRecord[0]);
				a.setId(id);
				System.out.println(a.getId());
				
				a.setTitle(nextRecord[1]);
				System.out.println(a.getTitle());
				
				a.setSinapsa(nextRecord[2]);
				System.out.println(a.getSinapsa());
				
				String zanr1 = nextRecord[3].substring(1, nextRecord[3].length()-1);
				a.setZanr(new ArrayList<String>(Arrays.asList(zanr1.split(","))));
				System.out.println(a.getZanr());
				
				a.setObjavljivanje(nextRecord[4]);
				System.out.println(a.getObjavljivanje());
				
				a.setEpizode(nextRecord[5]);
				System.out.println(a.getEpizode());
				
				a.setClanovi(Integer.parseInt(nextRecord[6]));
				System.out.println(a.getClanovi());
				
				Rang r = new Rang();
				r.setPopularnost(Integer.parseInt(nextRecord[7]));
				r.setBrrang(nextRecord[8]);
				r.setScore(nextRecord[9]);
				a.setRang(r);
				System.out.println(a.getRang());
				
				a.setSlika(nextRecord[10]);
				System.out.println(a.getSlika());
				
				a.setLink(nextRecord[11]);
				System.out.println(a.getLink());
				
				

				listings.add(a);
				System.out.println("----------");
				i++;
			}

			csvReader.close();

			System.out.println("Done creating json.");

			return listings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean writeToDb(List<Anime> listings) {
		try {
			ConnectionString connectionString = new ConnectionString("mongodb://nastava.is.pmf.uns.ac.rs");
			CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
			CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
					pojoCodecRegistry);
			MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.codecRegistry(codecRegistry).build();
			MongoClient mongoClient = MongoClients.create(clientSettings);
			MongoDatabase db = mongoClient.getDatabase("nosql");
			MongoCollection<Anime> col = db.getCollection("Anime", Anime.class);
			int i = 1;
			for (Anime l : listings) {
				System.out.println("Inserting listing " + i);
				col.insertOne(l);
				i++;
			}

			mongoClient.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		List<Anime> listings = createJson();
		writeToDb(listings);
	}
}
