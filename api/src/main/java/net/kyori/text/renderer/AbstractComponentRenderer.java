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
package net.kyori.text.renderer;

import net.kyori.text.BlockNbtComponent;
import net.kyori.text.Component;
import net.kyori.text.EntityNbtComponent;
import net.kyori.text.KeybindComponent;
import net.kyori.text.ScoreComponent;
import net.kyori.text.SelectorComponent;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;
import org.checkerframework.checker.nullness.qual.NonNull;

@SuppressWarnings("Duplicates")
public abstract class AbstractComponentRenderer<C> implements ComponentRenderer<C> {
  @Override
  public @NonNull Component render(final @NonNull Component component, final @NonNull C context) {
    if(component instanceof BlockNbtComponent) {
      return this.render((BlockNbtComponent) component, context);
    } else if(component instanceof EntityNbtComponent) {
      return this.render((EntityNbtComponent) component, context);
    } else if(component instanceof KeybindComponent) {
      return this.render((KeybindComponent) component, context);
    } else if(component instanceof ScoreComponent) {
      return this.render((ScoreComponent) component, context);
    } else if(component instanceof SelectorComponent) {
      return this.render((SelectorComponent) component, context);
    } else if(component instanceof TextComponent) {
      return this.render((TextComponent) component, context);
    } else if(component instanceof TranslatableComponent) {
      return this.render((TranslatableComponent) component, context);
    } else {
      return component;
    }
  }

  protected abstract @NonNull Component render(final @NonNull BlockNbtComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull EntityNbtComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull KeybindComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull ScoreComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull SelectorComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull TextComponent component, final @NonNull C context);

  protected abstract @NonNull Component render(final @NonNull TranslatableComponent component, final @NonNull C context);
}
