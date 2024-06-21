package dataset;

import java.util.List;

public class Anime {

	private Integer id;
	private String title;
	private String sinapsa;
	private List<String> zanr;
	private String objavljivanje;
	private String epizode;
	private Integer clanovi;
	private Rang rang; 
	private String slika;
	private String link;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinapsa() {
		return sinapsa;
	}
	public void setSinapsa(String sinapsa) {
		this.sinapsa = sinapsa;
	}
	public List<String> getZanr() {
		return zanr;
	}
	public void setZanr(List<String> zanr) {
		this.zanr = zanr;
	}
	public String getObjavljivanje() {
		return objavljivanje;
	}
	public void setObjavljivanje(String objavljivanje) {
		this.objavljivanje = objavljivanje;
	}
	public String getEpizode() {
		return epizode;
	}
	public void setEpizode(String epizode) {
		this.epizode = epizode;
	}
	public Integer getClanovi() {
		return clanovi;
	}
	public void setClanovi(Integer clanovi) {
		this.clanovi = clanovi;
	}
	public Rang getRang() {
		return rang;
	}
	public void setRang(Rang rang) {
		this.rang = rang;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
