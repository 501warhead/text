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
package net.kyori.text;

import net.kyori.text.format.TextDecoration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EntityNbtComponentTest extends AbstractComponentTest<EntityNbtComponent, EntityNbtComponent.Builder> {
  @Override
  EntityNbtComponent.Builder builder() {
    return EntityNbtComponent.builder().nbtPath("abc").selector("def");
  }

  @Test
  void testOf() {
    final EntityNbtComponent component = EntityNbtComponent.of("abc", "def");
    assertEquals("abc", component.nbtPath());
    assertEquals("def", component.selector());
    assertNull(component.color());
    for(final TextDecoration decoration : TextDecoration.values()) {
      assertEquals(TextDecoration.State.NOT_SET, component.decoration(decoration));
    }
  }

  @Test
  void testNbtPath() {
    final EntityNbtComponent c0 = EntityNbtComponent.of("abc", "def");
    final EntityNbtComponent c1 = c0.nbtPath("ghi");
    assertEquals("abc", c0.nbtPath());
    assertEquals("ghi", c1.nbtPath());
    assertEquals("def", c1.selector());
  }

  @Test
  void testSelector() {
    final EntityNbtComponent c0 = EntityNbtComponent.of("abc", "def");
    final EntityNbtComponent c1 = c0.selector("ghi");
    assertEquals("def", c0.selector());
    assertEquals("ghi", c1.selector());
    assertEquals("abc", c1.nbtPath());
  }

  @Test
  void testRebuildWithNoChanges() {
    final EntityNbtComponent component = EntityNbtComponent.of("test", "test");
    assertEquals(component, component.toBuilder().build());
  }
}
