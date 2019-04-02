package com.bckj.nacosdemo.commons.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.sql.Timestamp;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        super();

        SimpleModule module = new SimpleModule("v3", new Version(3, 0, 0, null));

        module.addSerializer(Timestamp.class, new JsonSerializer<Timestamp>() {
            @Override
            public void serialize(Timestamp value,
                                  JsonGenerator jsonGenerator,
                                  SerializerProvider provider)
                    throws IOException {
                jsonGenerator.writeString(
                        value != null ? DateFormatUtils.format(value.getTime(), "yyyy-MM-dd HH:mm:ss") : null
                );
            }
        });
        module.addDeserializer(Timestamp.class, new JsonDeserializer<Timestamp>() {
            @Override
            public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt)
                    throws IOException {
                try {
                    if (jp.getText() != null)
                        return new Timestamp(DateUtils.parseDate(jp.getText(), "yyyy-MM-dd HH:mm:ss").getTime());
                } catch (Exception e) {
                    return null;
                }
                return null;
            }
        });
        getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(
                    Object value,
                    JsonGenerator jg,
                    SerializerProvider sp) throws IOException {
                jg.writeString("");
            }
        });

        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        this.registerModule(module);

    }
}
