/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2018 KyoriPowered
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
package net.kyori.text.renderer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FriendlyComponentRendererTest {
  private static final Table<Locale, String, String> TRANSLATIONS = HashBasedTable.create();

  static {
    TRANSLATIONS.put(Locale.US, "test", "This is a test.");
    TRANSLATIONS.put(Locale.US, "cats", "{0} and {1} are cats.");
  }

  @Test
  void testSimple() {
    final FriendlyComponentRenderer<ComponentRenderer.Context> renderer = FriendlyComponentRenderer.from((locale, key) -> new MessageFormat(TRANSLATIONS.get(locale, key), locale));
    assertEquals(
      TextComponent.builder("This is a test.")
        .color(TextColor.YELLOW)
        .build(),
      renderer.render(
        TranslatableComponent
          .builder()
          .key("test")
          .color(TextColor.YELLOW)
          .build(),
        new ComponentRenderer.Context() {
          @Override
          public @NonNull Locale locale() {
            return Locale.US;
          }
        }
      )
    );
  }

  @Test
  void testComplex() {
    final FriendlyComponentRenderer<ComponentRenderer.Context> renderer = FriendlyComponentRenderer.from((locale, key) -> new MessageFormat(TRANSLATIONS.get(locale, key), locale));
    assertEquals(
      TextComponent.builder("")
        .color(TextColor.YELLOW)
        .append(TextComponent.of("kashike"))
        .append(TextComponent.of(" and "))
        .append(TextComponent.of("lucko"))
        .append(TextComponent.of(" are cats."))
        .build(),
      renderer.render(
        TranslatableComponent
          .builder()
          .key("cats")
          .args(
            TextComponent.of("kashike"),
            TextComponent.of("lucko")
          )
          .color(TextColor.YELLOW)
          .build(),
        new ComponentRenderer.Context() {
          @Override
          public @NonNull Locale locale() {
            return Locale.US;
          }
        }
      )
    );
  }

  @Test
  void testVeryComplex() {
    final FriendlyComponentRenderer<ComponentRenderer.Context> renderer = FriendlyComponentRenderer.from((locale, key) -> new MessageFormat(TRANSLATIONS.get(locale, key), locale));
    assertEquals(
      TextComponent.builder("")
        .color(TextColor.YELLOW)
        .append(TextComponent.of("This is a test."))
        .append(
          TextComponent.of("")
            .append(TextComponent.of("kashike"))
            .append(TextComponent.of(" and "))
            .append(TextComponent.of("lucko"))
            .append(TextComponent.of(" are cats."))
        )
        .build(),
      renderer.render(
        TextComponent
          .builder("")
          .append(
            TranslatableComponent
              .builder()
              .key("test")
              .build()
          ).append(
          TranslatableComponent
            .builder()
            .key("cats")
            .args(
              TextComponent.of("kashike"),
              TextComponent.of("lucko")
            )
            .build()
        )
          .color(TextColor.YELLOW)
          .build(),
        new ComponentRenderer.Context() {
          @Override
          public @NonNull Locale locale() {
            return Locale.US;
          }
        }
      )
    );
  }
}
