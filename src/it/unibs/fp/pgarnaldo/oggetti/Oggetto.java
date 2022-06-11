package it.unibs.fp.pgarnaldo.oggetti;

import java.util.Objects;

public class Oggetto {
	
	public final String nome;
	public final TipoOggetto tipo;
	
	public Oggetto(String nome, TipoOggetto tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public int hashCode() {
		return Objects.hash(nome, tipo);
	}
	
	public String toString() {
		return "%s (%s)".formatted(nome, tipo);
	}
	
}
