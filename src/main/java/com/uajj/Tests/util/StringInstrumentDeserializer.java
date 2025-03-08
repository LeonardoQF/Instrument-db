package com.uajj.Tests.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.uajj.Tests.model.entities.Guitar;
import com.uajj.Tests.model.entities.StringInstrument;
import com.uajj.Tests.model.entities.enums.GuitarType;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;

public class StringInstrumentDeserializer extends StdDeserializer<StringInstrument> {
	private static final long serialVersionUID = -1965419823008001978L;

	public StringInstrumentDeserializer() {
		this(null);
	}

	public StringInstrumentDeserializer(Class<?> vc) {
		super(vc);
	}

	/**
	 * Deserializes a StringInstrument into one of its subtypes based on the
	 * {@code guitarType} value passed. If the {@code guitarType} field is null or
	 * invalid, a regular StringInstrument object is returned instead. This method
	 * is to be used on request processing, and should not be used outside of that.
	 * @return A StringInstrument if the {@code guitarType} field null or not present, or a Guitar if it is.
	 */
	@Override
	public StringInstrument deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {

		// Transforms a JSON tree into a JSON node by decoding it with the JsonParser's
		// decoder.
		JsonNode node = p.getCodec().readTree(p);

		StringInstrument instrument = node.has("guitarType") ? new Guitar() : new StringInstrument();

		// This code is horrendous, verbose and should be improved in the near future.
		if (node.has("name")) {
			instrument.setName(node.get("name").asText());
		}
		if (node.has("brand")) {
			instrument.setBrand(node.get("brand").asText());
		}
		if (node.has("type")) {
			instrument.setType(InstrumentType.valueOf(node.get("type").asText()));
		}
		if (node.has("numberOfStrings")) {
			instrument.setNumberOfStrings(node.get("numberOfStrings").asInt());
		}
		if (node.has("wood")) {
			instrument.setWood(node.get("wood").asText());
		}
		if (node.has("stringMaterial")) {
			instrument.setStringMaterial(StringMaterial.valueOf(node.get("stringMaterial").asText()));
		}
		if (node.has("guitarType")) {
			instrument.setGuitarType(GuitarType.valueOf(node.get("guitarType").asText()));
			populateGuitarJson(node, (Guitar)instrument);
		}

		return instrument;
	}

	public void populateGuitarJson(JsonNode node, Guitar guitar) {
		if (node.has("numberOfFrets")) {
			guitar.setNumberOfFrets(node.get("numberOfFrets").asInt());
		}
		if (node.has("neckWood")) {
			guitar.setNeckWood(node.get("neckWood").asText());
		}
		if (node.has("hasWhammyBar")) {
			guitar.setHasWhammyBar(node.get("hasWhammyBar").asBoolean());
		}
		if (node.has("hasBuiltInTuner")) {
			guitar.setHasBuiltInTuner(node.get("hasBuiltInTuner").asBoolean());
		}
		if (node.has("pickups")) {
			guitar.setPickups(node.get("pickups").asText());
		}
		if (node.has("bodyShape")) {
			guitar.setBodyShape(node.get("bodyShape").asText());
		}
	}

}
