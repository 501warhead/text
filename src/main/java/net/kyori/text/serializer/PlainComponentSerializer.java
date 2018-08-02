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
package net.kyori.text.serializer;

import net.kyori.text.Component;
import net.kyori.text.ScoreComponent;
import net.kyori.text.SelectorComponent;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Function;

/**
 * A plain component serializer.
 *
 * @deprecated not recommended for general use
 */
@Deprecated
public class PlainComponentSerializer implements ComponentSerializer<Component, TextComponent, String> {
  private final Function<TranslatableComponent, String> translatable;

  public PlainComponentSerializer() {
    this((component) -> "");
  }

  public PlainComponentSerializer(final @NonNull Function<TranslatableComponent, String> translatable) {
    this.translatable = translatable;
  }

  @Override
  public @NonNull TextComponent deserialize(final @NonNull String input) {
    return TextComponent.of(input); //
  }

  @Override
  public @NonNull String serialize(final @NonNull Component component) {
    final StringBuilder sb = new StringBuilder();
    this.serialize(sb, component);
    return sb.toString();
  }

  public void serialize(final @NonNull StringBuilder sb, final @NonNull Component component) {
    if(component instanceof ScoreComponent) {
      sb.append(((ScoreComponent) component).value());
    } else if(component instanceof SelectorComponent) {
      sb.append(((SelectorComponent) component).pattern());
    } else if(component instanceof TextComponent) {
      sb.append(((TextComponent) component).content());
    } else if(component instanceof TranslatableComponent) {
      sb.append(this.translatable.apply((TranslatableComponent) component));
    } else {
      throw new IllegalArgumentException("Don't know how to turn " + component + " into a string");
    }

    for(final Component child : component.children()) {
      this.serialize(sb, child);
    }
  }
}
