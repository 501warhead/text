/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2019 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.text.serializer.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import org.junit.jupiter.api.Test;

import static net.kyori.text.serializer.gson.AbstractComponentTest.array;
import static net.kyori.text.serializer.gson.AbstractComponentTest.object;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GsonComponentSerializerTest {
  @Test
  void testDeserializePrimitive() {
    assertEquals(TextComponent.of("potato"), GsonComponentSerializer.GSON.fromJson(new JsonPrimitive("potato"), Component.class));
  }

  @Test
  void testDeserializeArray_empty() {
    assertThrows(JsonParseException.class, () -> GsonComponentSerializer.GSON.fromJson(new JsonArray(), Component.class));
  }

  @Test
  void testDeserializeArray() {
    assertEquals(TextComponent.of("Hello, ").append(TextComponent.of("world.")), GsonComponentSerializer.GSON.fromJson(array(array -> {
      array.add(object(object -> object.addProperty(GsonComponentSerializer.TEXT, "Hello, ")));
      array.add(object(object -> object.addProperty(GsonComponentSerializer.TEXT, "world.")));
    }), Component.class));
  }
}
