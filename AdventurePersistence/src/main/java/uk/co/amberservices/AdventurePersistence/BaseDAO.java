package uk.co.amberservices.AdventurePersistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class BaseDAO {
	
	public HashMap<String, Object> readFile(final String filename)
    {

        String data = null;
        BufferedReader in;

        try
        {
            in = new BufferedReader(new FileReader(filename));
            String str;
            while ((str = in.readLine()) != null)
            {
                data = str;
            }
            in.close();

        }
        catch (final IOException e)
        {
            System.out.println(e);
        }
        return this.buildMap(data);
    }

	private HashMap<String,Object> buildMap(final String s)
    {
        HashMap<String, Object> messageMap = new HashMap<String, Object>();
        final JsonParser parser = Json.createParser(new StringReader(s));
        while (parser.hasNext())
        {
            Event event = parser.next();
            if (event == JsonParser.Event.KEY_NAME)
            {
                final String key = parser.getString();
                event = parser.next();
                if (event == JsonParser.Event.VALUE_STRING)
                {
                    final String value = parser.getString();
                    messageMap.put(key, value);
                }
                else if (event == JsonParser.Event.VALUE_NUMBER)
                {
                    final String value = String.valueOf(parser.getInt());
                    messageMap.put(key, value);
                }
                else if (event == JsonParser.Event.START_OBJECT)
                {
                    messageMap.put(key, this.parseObject(parser));
                }
                else if (event == JsonParser.Event.START_ARRAY)
                {
                    messageMap.put(key, this.parseArray(parser));
                }
            }
        }
        return messageMap;
    }
	
	private List<Object> parseArray(final JsonParser parser)
    {
        Event event = null;
        final List<Object> objects = new ArrayList<Object>();
        while (event != JsonParser.Event.END_ARRAY)
        {
            event = parser.next();
            if (event == JsonParser.Event.START_OBJECT)
            {
                final Map<String, Object> object = parseObject(parser);
                objects.add(object);
            }
            else if (event == JsonParser.Event.VALUE_STRING || event == JsonParser.Event.VALUE_NUMBER)
            {
                final String object = parser.getString();
                objects.add(object);
            }
        }
        return objects;
    }

    private Map<String, Object> parseObject(final JsonParser parser)
    {
        Event event = null;
        final Map<String, Object> object = new HashMap<String, Object>();
        while (event != JsonParser.Event.END_OBJECT)
        {
            event = parser.next();
            if (event == JsonParser.Event.KEY_NAME)
            {
                final String key = parser.getString();
                event = parser.next();
                if (event == JsonParser.Event.VALUE_STRING || event == JsonParser.Event.VALUE_NUMBER)
                {
                    final String value = parser.getString();
                    object.put(key, value);
                }
                else if (event == JsonParser.Event.VALUE_TRUE)
                {
                    object.put(key, true);
                }
                else if (event == JsonParser.Event.VALUE_FALSE)
                {
                    object.put(key, false);
                }
                else if (event == JsonParser.Event.START_ARRAY)
                {
                    object.put(key, parseArray(parser));
                }
            }
        }

        return object;
    }
}
