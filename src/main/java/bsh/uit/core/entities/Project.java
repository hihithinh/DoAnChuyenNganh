package bsh.uit.core.entities;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable{

	private static final long serialVersionUID = 1L;

	
	//Project ID
	private String id;
	
	//Ten Project
	private String name;
	
	//User tao Project
	private User user;
	
	//Ten nghe si/ban nhac
	private String artist_name;
	
	//Trang thai Project
	private int status;
	
	//Loai am nhac cua Project
	private Type music_type;
	
	//So luong Piano can trong Project
	private int needPiano;
	
	//So luong Organ can trong Project
	private int needOrgan;
		
	//So luong Vocal can trong Project
	private int needVocal;
	
	//So luong Guitar can trong Project
	private int needGuitar;

	//So luong Violon can trong Project
	private int needViolon;
		
	//So luong Drum can trong Project
	private int needDrum;
		
	//So luong Ukulele can trong Project
	private int needUkulele;
	
	//So luong Harmonica can trong zProject
	private int needHarmonica;
	
	//Ngay tao Project
	private Date created_day;
	
	//Ngay cap nhat Project
	private Date update_day;
	
	//Mo ta cua Project
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Type getMusic_type() {
		return music_type;
	}

	public void setMusic_type(Type music_type) {
		this.music_type = music_type;
	}

	public int getNeedPiano() {
		return needPiano;
	}

	public void setNeedPiano(int needPiano) {
		this.needPiano = needPiano;
	}

	public int getNeedOrgan() {
		return needOrgan;
	}

	public void setNeedOrgan(int needOrgan) {
		this.needOrgan = needOrgan;
	}

	public int getNeedVocal() {
		return needVocal;
	}

	public void setNeedVocal(int needVocal) {
		this.needVocal = needVocal;
	}

	public int getNeedGuitar() {
		return needGuitar;
	}

	public void setNeedGuitar(int needGuitar) {
		this.needGuitar = needGuitar;
	}

	public int getNeedViolon() {
		return needViolon;
	}

	public void setNeedViolon(int needViolon) {
		this.needViolon = needViolon;
	}

	public int getNeedDrum() {
		return needDrum;
	}

	public void setNeedDrum(int needDrum) {
		this.needDrum = needDrum;
	}

	public int getNeedUkulele() {
		return needUkulele;
	}

	public void setNeedUkulele(int needUkulele) {
		this.needUkulele = needUkulele;
	}

	public int getNeedHarmonica() {
		return needHarmonica;
	}

	public void setNeedHarmonica(int needHarmonica) {
		this.needHarmonica = needHarmonica;
	}

	public Date getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Date created_day) {
		this.created_day = created_day;
	}

	public Date getUpdate_day() {
		return update_day;
	}

	public void setUpdate_day(Date update_day) {
		this.update_day = update_day;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
