package com.sap.hana.cloud.samples.granny.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.annotations.OptimisticLockingType;

/**
 * Base class for all domain model objects.
 */
@MappedSuperclass
@OptimisticLocking(type=OptimisticLockingType.VERSION_COLUMN, cascade = true) 
public abstract class BaseObject 
{

	/**
	 * The ID of the object.
	 */
	@Id
    @Column(name="ID", length = 36)
    private String id = null;

	/**
	 * The {@link Date} the object was created at.
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE", updatable = false)
    private Date createdAt = null;
 
    /**
	 * The {@link Date} the object was last modified at.
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFICATION_DATE")
    private Date lastModifiedAt = null;

    /**
     * ID of the user who created the object.
     */
    @Column(name="CREATED_BY", updatable = false, length = 20)
    private String createdBy = null;
    
    /**
     * ID of the user who was the last to modify the object.
     */
    @Column(name="MODIFIED_BY", length = 20)
    private String lastModifiedBy = null;
    
    /**
     * The version number used for optimistic locking.
     * 
     * @see http://en.wikibooks.org/wiki/Java_Persistence/Locking
     * @see http://eclipse.org/eclipselink/documentation/2.4/jpa/extensions/a_optimisticlocking.htm
     */
    @Column(name="VERSION")
    @Version
    private Long version = 0L;
    
    /**
     * Life-cycle event callback, which automatically sets the last modification date.  
     */
    @PreUpdate
    protected void updateAuditInformation() 
    {
        lastModifiedAt = new Date();
        
        // TODO - obtain currently logged-on user
    }

    /**
     * Life-cycle event callback, which automatically creates a unique ID for the object
     * and populates its audit information.  
     */
    @PrePersist
    protected void generateAuditInformation() 
    {
        id = UUID.randomUUID().toString();     
        
        final Date now = new Date();
        
        createdAt = now;
        lastModifiedAt = now;
        
        // TODO - obtain currently logged-on user
    }

    public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
    
	public Date getCreatedAt()
	{
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt()
	{
		return this.lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt)
	{
		this.lastModifiedAt = lastModifiedAt;
	}

	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy()
	{
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy)
	{
		this.lastModifiedBy = lastModifiedBy;
	}

	public Long getVersion()
	{
		return version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return new ToStringBuilder(this).append("id", this.id).append("createdAt", this.createdAt).append("createdBy", this.createdBy)
	           .append("lastModifiedAt", this.lastModifiedAt).append("lastModifiedBy", this.lastModifiedBy).append("version", this.version).toString();
	}
}
