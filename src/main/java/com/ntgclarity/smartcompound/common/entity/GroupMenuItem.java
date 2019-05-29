package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_menu_item_group")
public class GroupMenuItem extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(generator = "my_gen",strategy =GenerationType.SEQUENCE )
	@SequenceGenerator(name = "my_gen", sequenceName = "ng_nts_menu_item_group_recid_seq")
	@Column(name = "recid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "recid")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "menu_item_id", referencedColumnName = "recid")
	private MenuItem menuItem;

	
	
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	


}
