package com.ricoh.orders.controller;

import java.util.ArrayList;
import java.util.List;

import com.ricoh.orders.model.Queue;
import com.ricoh.orders.model.Tenant;
import com.ricoh.orders.model.User;


public class SizeAdapter {
	
	 	
	static public Iterable<Queue> sizeXS(Iterable<Queue> qs) {
		if( qs == null ) return null;
		
		List<Queue> r = new ArrayList<Queue> ();
		for( Queue q: qs )
			r.add( sizeXS( q ) );
		
		return r;
	}
	
	static public Iterable<Queue> queueS(Iterable<Queue> qs) {
		if( qs == null ) return null;
		
		List<Queue> r = new ArrayList<Queue> ();
		for( Queue q: qs )
			r.add( sizeS( q ) );
		
		return r;
	}	
	
	static public Queue sizeXS(Queue q) {
		if( q == null ) return null;
		
		Queue r = new Queue();
		r.setId( q.getId() );
		r.setName( q.getName() );
		return r;
	}

	static public Queue sizeS(Queue q) {
		if( q == null ) return null;
		
		Queue r = new Queue();
		r.setId( q.getId() );
		r.setName( q.getName() );
		r.setNumberOfStations( q.getNumberOfStations() );
		
		return r;
	}

	public static Iterable<User> userS(Iterable<User> users) {
	if( users== null ) return null;
		
		List<User> r = new ArrayList<User> ();
		for( User u: users )
			r.add( sizeS( u ) );
		
		return r;	
	}
	
	public static Iterable<User> userM(Iterable<User> users) {
		if( users== null ) return null;
			
		List<User> r = new ArrayList<User> ();
		for( User u: users )
			r.add( sizeM( u ) );
		
		return r;	
	}


	public static User sizeS(User u) {
		if( u == null ) return null;
		User r = new User( "", "" );
		r.setId( u.getId() );
		r.setName( u.getName() );
		r.setFirstName( u.getFirstName() );
		r.setSecondFamilyName( u.getSecondFamilyName() );
		r.setShortName( r.getShortName() );
		r.setTenant( sizeXS( u.getTenant() ) );
		///r.setCurrentQueue( sizeXS( u.getCurrentQueue() ));
	
		return r;
	}
	
	public static User sizeM(User u) {
		if( u == null ) return null;
		
		User r = new User( "", "" );
		r.setId( u.getId() );
		r.setName( u.getName() );
		r.setFirstName( u.getFirstName() );
		r.setFamilyName(u.getFamilyName());
		r.setSecondFamilyName( u.getSecondFamilyName() );
		r.setShortName( u.getShortName() );
		///r.setWorkProfiles( u.getWorkProfiles() );
		r.setRoles( u.getRoles() );
		r.setTenant( sizeXS( u.getTenant() ) );
		///r.setCurrentQueue( sizeS( u.getCurrentQueue() ));
		///r.setCurrentStation( u.getCurrentStation() );
		
		return r;
	}
	
	
	public static Tenant sizeXS(Tenant t) {
		if( t == null ) return null;
		Tenant r = new Tenant();
		r.setId( t.getId() );
		t.setName( t.getName() );
		return t;
	}

}
