package com.ruben.bluewave.service;

import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.PlanDTO;

public class ToStringTest {

	public static void main(String[] args) {
		PlanDTO pl = new PlanDTO();
		System.out.println(pl);

		Cliente c = new Cliente();
		c.setNombre("Ruben");
		System.out.println(c);
	}

}
