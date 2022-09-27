package com.garden.helper.data.payloads.plants;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;

public class BonsaiViewPayload {
	
	private Long id;
	
	private String name;
	
	private String thumbnailImage;
	
	private EBonsaiStyle bonsaiStyle;
	
	private EBonsaiType bonsaiType;
	
	/**
	 * 
	 * @param bonsai
	 * @return {@linkplain BonsaiViewPayload} object, {@code thumbnailPicture}
	 * will not be set here since compression decompression will be better
	 * perform in service layer. For getting a {@linkplain BonsaiViewPayload}
	 * with {@code thumbnailPicture} included we can use
	 * {@link com.garden.helper.services.BonsaiService#createBonsaiViewPayload()}
	 */
	public static BonsaiViewPayload build(Bonsai bonsai) {
		BonsaiViewPayload bonsaiView = new BonsaiViewPayload();
		bonsaiView.setId(bonsai.getId());
		bonsaiView.setName(bonsai.getName());
		bonsaiView.setBonsaiStyle(bonsai.getStyle());
		bonsaiView.setBonsaiType(bonsai.getType());
		
		return bonsaiView;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public EBonsaiStyle getBonsaiStyle() {
		return bonsaiStyle;
	}

	public void setBonsaiStyle(EBonsaiStyle bonsaiStyle) {
		this.bonsaiStyle = bonsaiStyle;
	}

	public EBonsaiType getBonsaiType() {
		return bonsaiType;
	}

	public void setBonsaiType(EBonsaiType bonsaiType) {
		this.bonsaiType = bonsaiType;
	}

}
